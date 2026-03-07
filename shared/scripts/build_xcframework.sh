#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
SHARED_DIR="$PROJECT_ROOT/shared"
OUTPUT_DIR="$SHARED_DIR/build/XCFrameworks/release"
XCFRAMEWORK_NAME="KMPShared"
ZIP_NAME="$XCFRAMEWORK_NAME.xcframework.zip"

echo "Building XCFramework..."
cd "$PROJECT_ROOT"
./gradlew :shared:assembleKMPSharedReleaseXCFramework

echo "Packaging XCFramework..."
cd "$OUTPUT_DIR"
rm -f "$ZIP_NAME"
zip -r "$ZIP_NAME" "$XCFRAMEWORK_NAME.xcframework"

echo "Calculating checksum..."
CHECKSUM=$(shasum -a 256 "$ZIP_NAME" | awk '{print $1}')

echo ""
echo "=========================================="
echo "XCFramework built and packaged successfully!"
echo "=========================================="
echo ""
echo "Output: $OUTPUT_DIR/$ZIP_NAME"
echo "Checksum (SHA256): $CHECKSUM"
echo ""
echo "Update your Package.swift binaryTarget with:"
echo ""
echo ".binaryTarget("
echo "    name: \"$XCFRAMEWORK_NAME\","
echo "    url: \"https://github.com/AntonioNoguera/kmp-spike/releases/download/vX.X.X/$ZIP_NAME\","
echo "    checksum: \"$CHECKSUM\""
echo "),"
echo ""
