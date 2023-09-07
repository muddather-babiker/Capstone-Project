const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    addTrip: path.resolve(__dirname, 'src', 'pages', 'addTrip.js'),
    updatetrip: path.resolve(__dirname, 'src', 'pages', 'updatetrip.js'),
    addMember: path.resolve(__dirname, 'src', 'pages', 'addMember.js'),
    member: path.resolve(__dirname, 'src', 'pages', 'member.js'),
    addDriver: path.resolve(__dirname, 'src', 'pages', 'addDriver.js'),
    driver: path.resolve(__dirname, 'src', 'pages', 'driver.js'),
    home: path.resolve(__dirname, 'src', 'pages', 'home.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
