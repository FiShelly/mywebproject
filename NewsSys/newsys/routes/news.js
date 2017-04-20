var express = require('express');
var router = express.Router();
var multiparty = require('multiparty');
var util = require('util');
var fs = require('fs');
var moment = require("moment");

/* 根据url路由，来确定不同的操作 */
router.post('/:action', function (req, res, next) {
    //如果登录则可以进行相应操作，否则跳转到登录页面
    if (req.session.user || req.body.type) {
        //判断操作
        var action = req.param("action");
        //分页查询全部新闻
        if (action == "queryAll") {
            var ls = req.body.ls;
            var cp = req.body.cp;
            var a = (cp - 1) * ls;
            var b = cp * ls;
            var data = {news: "", allPage: ""};
            req.models.new.count({}, function (err, count) {
                count = parseInt(count / 9) + 1;
                data.allPage = count;
            });
            var query = req.models.new;
            //判断是客户端还是管理端的查询
            if (req.body.type) {
                query = query.find({type: req.body.type});
            } else {
                query = query.find({});
            }
            query.limit(b).offset(a).orderRaw("?? DESC", ["date"]).run(function (error, news) {
                if (error == null) {
                    data.news = news;
                    res.json(data);
                }
            });
        } else if (action == "queryOne") {//查询某一条新闻，用于修改
            var id = req.body.id;
            req.models.new.find({id: id}, function (error, news) {
                if (error == null) {
                    res.json(news[0]);
                }
            });
        } else if (action == "insert") {  //增加一条新闻
            //图片上传操作。
            var form = new multiparty.Form({uploadDir: '../public/upload/'});
            form.parse(req, function (err, fields, files) {
                console.log(files);
                if (err) {
                    console.log('parse error: ' + err);
                } else {
                    var dstPath = "";
                    if (files.data[0].originalFilename) {
                        var fileName = files.data[0].originalFilename.split(".")[1];
                        dstPath = 'upload/' + req.session.user + "_" + moment().format("YYYYMMDDHHmmss") + "." + fileName;
                        //重命名为真实文件名
                        fs.rename(files.data[0].path,"../public/"+dstPath, function (err) {
                            if (err) {
                                console.log('rename error: ' + err);
                            } else {
                                console.log('rename ok');
                            }
                        });
                    } else {
                        fs.unlink(files.data[0].path);
                        if (!fields.rphoto) {
                            dstPath = fields.rphoto;
                        }
                    }
                    //判断是增加新闻还是修改新闻
                    if (!fields.id) {
                        req.models.new.create({
                            title: fields.title[0],
                            summary: fields.summary[0],
                            imgdir: dstPath,
                            date: moment().format("YYYY-MM-DD HH:mm:ss"),
                            state: fields.state[0],
                            type: fields.type[0]
                        }, function (err) {
                            if (err) {
                                res.json(2);
                            } else {
                                res.json(1);
                            }
                        });
                    } else {
                        req.models.new.get(fields.id, function (error, news) {
                            if (error == null) {
                                news.title = fields.title[0];
                                news.summary = fields.summary[0];
                                news.imgdir = dstPath;
                                news.date = moment().format("YYYY-MM-DD HH:mm:ss");
                                news.state = fields.state;
                                news.type = fields.type;
                                news.save(function (err) {
                                    if (err == null) {
                                        res.json(1);
                                    } else {
                                        res.json(2);
                                    }
                                });
                            } else {
                                res.json(2);
                            }
                        });
                    }

                }
            });
        } else if (action == "delete") {  //删除新闻
            req.models.new.get(req.body.id, function (error, news) {
                if (error == null) {
                    news.remove(function (err) {
                        if (err == null) {
                            res.json(1);
                        } else {
                            res.json(2);
                        }
                    });
                } else {
                    res.json(2);
                }
            });
        }
    } else {
        res.json(-1);
    }
});

module.exports = router;
