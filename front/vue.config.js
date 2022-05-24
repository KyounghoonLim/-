const { defineConfig } = require('@vue/cli-service')
const webpack = require('webpack')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy : 'http://localhost:8080'
  },
  configureWebpack: {
    resolve: {
      alias: {
        process: "process/browser"
      },
      fallback: {
        "stream": require.resolve("stream-browserify"),
        "http": require.resolve("stream-http"),
        "https": require.resolve("https-browserify"),
        "os": require.resolve("os-browserify/browser"),
        "path": require.resolve("path-browserify"),
        "crypto": require.resolve("crypto-browserify"),
        "zlib": require.resolve("zlib-browserify"),
        "Buffer": require.resolve("buffer"),
        "buffer": require.resolve("buffer"),
        "request": require.resolve("request"),
        "process": require.resolve("process"),
        "fs": false,
        "child_process": false,
      }
    },
    plugins: [
      new webpack.ProvidePlugin({
        Buffer: ['buffer', 'Buffer']
      }),
      new webpack.ProvidePlugin({
        process: 'process/browser'
      })
    ]
  }
})
