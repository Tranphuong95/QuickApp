const webpack = require('webpack');
const webpackMerge = require('webpack-merge');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const WorkboxPlugin = require('workbox-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const MomentLocalesPlugin = require('moment-locales-webpack-plugin');
const sass = require('sass');

const utils = require('./utils.js');
const commonConfig = require('./webpack.common.js');

const ENV = 'production';

module.exports = webpackMerge(commonConfig({ env: ENV }), {
  // devtool: 'source-map', // Enable source maps. Please note that this will slow down the build
  mode: ENV,
  entry: {
    main: './src/main/webapp/app/index'
  },
  output: {
    path: utils.root('build/resources/main/static/'),
    filename: 'app/[name].[hash].bundle.js',
    chunkFilename: 'app/[name].[hash].chunk.js'
  },
  module: {
    rules: [
      {
        enforce: 'pre',
        test: /\.s?css$/,
        loader: 'stripcomment-loader'
      },
      {
        test: /\.(sa|sc|c)ss$/,
        use: [
          {
            loader: MiniCssExtractPlugin.loader,
            options: {
              publicPath: '../'
            }
          },
          'css-loader',
          'postcss-loader',
          {
            loader: 'sass-loader',
            options: { implementation: sass }
          }
        ]
      }
    ]
  },
  optimization: {
    runtimeChunk: false,
    minimizer: [
      new TerserPlugin({
        cache: true,
        parallel: true,
        // sourceMap: true, // Enable source maps. Please note that this will slow down the build
        terserOptions: {
          ecma: 6,
          toplevel: true,
          module: true,
          beautify: false,
          comments: false,
          compress: {
            warnings: false,
            ecma: 6,
            module: true,
            toplevel: true
          },
          output: {
              comments: false,
              beautify: false,
              indent_level: 2,
              ecma: 6
          },
          mangle: {
            keep_fnames: true,
            module: true,
            toplevel: true
          }
        }
      }),
      new OptimizeCSSAssetsPlugin({})
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
      // Options similar to the same options in webpackOptions.output
      filename: 'content/[name].[hash].css',
      chunkFilename: 'content/[name].[hash].css'
    }),
    new MomentLocalesPlugin({
      localesToKeep: [
        'vi'
        // jhipster-needle-i18n-language-moment-webpack - JHipster will add/remove languages in this array
      ]
    }),
    new webpack.LoaderOptionsPlugin({
      minimize: true,
      debug: false
    }),
    new WorkboxPlugin.GenerateSW({
      clientsClaim: true,
      skipWaiting: true,
      exclude: [/swagger-ui/]
    })
  ]
});

// todo test 8/1
// var webpack = require("webpack");
// var CopyWebpackPlugin = require('copy-webpack-plugin');
//
// module.exports = {
//   entry: {
//     basic: './src/basic.jsx',
//     edit_inline: './src/edit_inline.jsx',
//     full_editor: './src/full_editor.jsx',
//     two_way_binding: './src/two_way_binding.jsx',
//     manual_initialization: './src/manual_initialization.jsx',
//     init_on_image: './src/init_on_image.jsx',
//     init_on_button: './src/init_on_button.jsx',
//     init_on_link: './src/init_on_link.jsx',
//     init_on_input: './src/init_on_input.jsx',
//     custombutton: './src/custombutton.jsx'
//   },
//
//   optimization: {
//     concatenateModules: false
//   },
//
//   module: {
//     rules: [
//       {
//         test: /\.jsx$/,
//         use: {
//           loader: 'babel-loader',
//           options: {
//             cacheDirectory: true,
//             presets: ['react','es2015', 'stage-2']
//           }
//         }
//       }, {
//         test: /\.css$/,
//         use: [
//           'style-loader',
//           'css-loader'
//         ]
//       },
//       {
//         test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
//         use: "url-loader?limit=10000&mimetype=application/font-woff"
//       }, {
//         test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
//         use: "url-loader?limit=10000&mimetype=application/font-woff"
//       }, {
//         test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
//         use: "url-loader?limit=10000&mimetype=application/octet-stream"
//       }, {
//         test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
//         use: "file-loader"
//       }, {
//         test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
//         use: "url-loader?limit=10000&mimetype=image/svg+xml"
//       }
//     ]
//   },
//
//   resolve: {
//     alias: {
//       "react-froala-wysiwyg": '../../dist'
//     },
//     modules: ['node_modules']
//   },
//
//   output: {
//     path: __dirname + '/dist/',
//     filename: '[name].js',
//     publicPath: '/'
//   },
//
//   plugins: [
//     new webpack.ProvidePlugin({
//
//     }),
//
//     new CopyWebpackPlugin([{ from: './src/index.html'}, {from: './src/image.jpg'} ])
//   ]
// };