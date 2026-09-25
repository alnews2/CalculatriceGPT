#!/usr/bin/env python3
"""Bump the Android application version and promote CHANGELOG [Unreleased]."""

from __future__ import annotations

import argparse
import datetime as dt
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
GRADLE = ROOT / "app" / "build.gradle.kts"
CHANGELOG = ROOT / "CHANGELOG.md"

SEMVER = re.compile(
    r"^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)"
    r"(?:-([0-9A-Za-z-]+(?:\.[0-9A-Za-z-]+)*))?"
    r"(?:\+([0-9A-Za-z-]+(?:\.[0-9A-Za-z-]+)*))?$"
)
VERSION_RE = re.compile(r'(versionName\s*=\s*")([^"]+)(")')


def read_version() -> str:
    text = GRADLE.read_text(encoding="utf-8")
    match = VERSION_RE.search(text)
    if not match:
        raise SystemExit("versionName not found in app/build.gradle.kts")
    version = match.group(2)
    if not SEMVER.fullmatch(version):
        raise SystemExit(f"Current version is not valid SemVer 2.0.0: {version}")
    return version


def bump(version: str, level: str) -> str:
    match = SEMVER.fullmatch(version)
    if not match:
        raise SystemExit(f"Invalid SemVer 2.0.0: {version}")
    major, minor, patch = map(int, match.groups()[:3])
    if level == "major":
        major, minor, patch = major + 1, 0, 0
    elif level == "minor":
        minor, patch = minor + 1, 0
    else:
        patch += 1
    return f"{major}.{minor}.{patch}"


def main() -> None:
    parser = argparse.ArgumentParser(
        description="Bump versionName and promote CHANGELOG.md [Unreleased]."
    )
    parser.add_argument("level", choices=("patch", "minor", "major"))
    args = parser.parse_args()

    old = read_version()
    new = bump(old, args.level)

    gradle = GRADLE.read_text(encoding="utf-8")
    gradle = VERSION_RE.sub(rf"\g<1>{new}\g<3>", gradle, count=1)
    GRADLE.write_text(gradle, encoding="utf-8")

    changelog = CHANGELOG.read_text(encoding="utf-8")
    marker = "## [Unreleased]"
    if marker not in changelog:
        raise SystemExit("CHANGELOG.md does not contain [Unreleased]")
    today = dt.date.today().isoformat()
    changelog = changelog.replace(marker, f"## [Unreleased]\n\n## [{new}] - {today}", 1)
    CHANGELOG.write_text(changelog, encoding="utf-8")

    print(f"{old} -> {new}")
    print("Updated app/build.gradle.kts and CHANGELOG.md.")
    print("Review the generated changelog/version, then run CI before merging.")


if __name__ == "__main__":
    main()
