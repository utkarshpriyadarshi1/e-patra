```javascript
const path = require('path');

module.exports = {
    entry: './src/main/frontend/index.js',
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/built'),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    devServer: {
        contentBase: path.join(__dirname, 'src/main/resources/static'),
        compress: true,
        port: 9000
    }
};
```