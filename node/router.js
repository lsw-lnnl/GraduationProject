// 加载所需模块
var http = require('http');
var url = require('url');
var fs = require('fs');

var host = '127.0.0.1';
var port = 8080;

http.createServer(function(req,res){
    var pathname = url.parse(req.url).pathname;
    console.log('Request for ' + pathname + ' received.');
        function showPaper(path,status){
            var content = fs.readFileSync(path);
            res.writeHead(status, { 'Content-Type': 'text/html;charset=utf-8' });
            res.write(content);
            res.end();
        }
        switch(pathname){
        //'首页'
        case '/':
        case '/home':
            showPaper('./view/home.html',200);
            break;
        //'about页'
        case '/about':
            showPaper('./view/about.html',200);   
            break;
        //'404页'
        default:
            showPaper('./view/404.html',404);
            break;                            
    }    
}).listen(port, host);