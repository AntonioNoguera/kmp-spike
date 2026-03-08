// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "KMPShared",
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: "KMPShared",
            targets: ["KMPShared"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "KMPShared",
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.5/KMPShared.xcframework.zip",
            checksum: "f83c8e9c78a6a4cef35d0e3cd6ce8fa06729f913bff308d38933a43e5f44bd08"
        ),
    ]
)
