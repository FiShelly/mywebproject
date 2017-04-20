var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var orm = require('orm');
var ejs = require('ejs');
var routes = require('./routes/index');
var users = require('./routes/users');
var back = require('./routes/back');
var news = require('./routes/news');
var client = require('./routes/newsclient');
var session = require('express-session');
var settings = require('./settings');
var xss = require('xss');
var csrf = require('csurf');
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.engine("html", ejs.renderFile);
// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(session({secret: 'keyboard cat'}));

app.use(csrf({ cookie: true }));
app.use(function (req, res, next){
    res.locals._csrf = req.csrfToken();
    next();
});

app.use(
    orm.express(settings.type+'://'+settings.user+':'+settings.pwd+'@'+settings.host+':'+settings.port+'/'+settings.db, {
        define: function (db, models, next) {
            models.user = db.define("user", {
                loginId: {type: 'serial', key: true},
                password: String
            });
            models.new = db.define("news", {
                    id: {type: 'serial', key: true},
                    title: String,
                    summary: String,
                    imgdir: String,
                    type: String,
                    state: String,
                    date: String
                }
            );
            models.new = db.define("news", {
                    id: {type: 'serial', key: true},
                    title: String,
                    summary: String,
                    imgdir: String,
                    type: String,
                    state: String,
                    date: String
                }
            );

            next();
        }
    }));
app.use('/index', routes);
app.use('/users', users);
app.use('/back/index', back);
app.use('/news', news);
app.use('/client', client);
// catch 404 and forward to error handler
app.use(function (req, res, next) {
    var err = new Error('Not Found---请求错误，请重试');
    err.status = 404;
    next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
    app.use(function (err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err
        });
    });
}

// production error handler
// no stacktraces leaked to user
app.use(function (err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {}
    });
});


module.exports = app;
