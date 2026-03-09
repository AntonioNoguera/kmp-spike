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
            checksum: "1ad0c461d723896a74a86c3f04a08dea46b929ab7d6c3789f4d7d1fa332c7d16"
        ),
    ]
)
