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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.4/KMPShared.xcframework.zip",
            checksum: "ce22c4be751bd12a928ff19fedfb26ecc1522d5c1d15b15f7b1138e8ac5c3440"
        ),
    ]
)
