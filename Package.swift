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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.3/KMPShared.xcframework.zip",
            checksum: "87f79e399f17ec72ac2b0e357e2f266b4b344c2fcfef7d876050768feed74918"
        ),
    ]
)
