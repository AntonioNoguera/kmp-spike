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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.1.3/KMPShared.xcframework.zip",
            checksum: "c59aa8f4586b63879e2835ca316dad2ac0f203483bd683be5868c824e7cd4e2a"
        ),
    ]
)
