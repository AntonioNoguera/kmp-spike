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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.1.4/KMPShared.xcframework.zip",
            checksum: "7fe5a13051bb4383690b5bd598894169d5e97b8e64ceb38b60ad36761341f01f"
        ),
    ]
)
