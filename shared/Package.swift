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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.0.6/KMPShared.xcframework.zip",
            checksum: "5040ef184975df1c2bb3923090c99a242ad93429dff4cbdd4a5bc33b9bde1800"
        ),
    ]
)
