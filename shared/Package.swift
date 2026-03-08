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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.7/KMPShared.xcframework.zip",
            checksum: "d5c325bcd0dfd3bec2133a77fdf05c4f02f7e058ca175d6335810f8365c42c62"
        ),
    ]
)
