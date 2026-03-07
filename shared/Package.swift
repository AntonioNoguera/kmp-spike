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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.0/KMPShared.xcframework.zip",
            checksum: "d7664a857408e97b5e826d2e51710f3170747a2b748c52f0f01afd8506ab9890"
        ),
    ]
)
