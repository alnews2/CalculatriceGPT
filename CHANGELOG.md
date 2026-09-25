# Changelog

## [Unreleased]

### Added

- Added an application title banner.
- Added a dedicated button to close the application.
- Added a 5 mm screen-edge margin around the main interface elements.

### Fixed

- Release workflow now compares the application version with the latest published SemVer release and skips duplicate-version releases.

## [0.1.0] - 2026-09-25

### Added

- Initial Android project skeleton.
- Kotlin and Jetpack Compose setup.
- Four-operation calculator UI.
- Separate calculator domain logic.
- Unit tests for the four operations and division by zero.
- GitHub Actions workflow running tests and building a debug APK.
- Automated GitHub Release workflow triggered after successful CI on `main`.
- Automatic creation of version tags using the `v<version>` convention.
- Strict SemVer 2.0.0 validation for the application version.
- Automatic attachment of the validated APK to the GitHub Release.
- Automatic use of the matching changelog section as the GitHub Release notes.
