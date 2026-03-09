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
            url: "https://github.com/AntonioNoguera/kmp-spike/releases/download/v1.1.2/KMPShared.xcframework.zip",
            checksum: "929e4d70ba72619dd1fbed31f4ed5c734e9026ebed2a928eb9c521dce8ca74ab"
        ),
    ]
)
