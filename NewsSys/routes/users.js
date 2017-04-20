var express = require('express');
var router = express.Router();

/* 根据url路由，来确定不同的操作 */
router.post('/:action', function (req, res, next) {
    var action = req.param("action");
    var ploginId = req.body.loginId;
    var ppassword = req.body.password;
    //登录操作
    if (action == "login") {
        req.models.user.find({loginId: ploginId, password: ppassword}, function (err, user) {
            if (user.length == 1) {
                req.session.user = user[0].loginId;
                res.redirect("/back/index");
            } else {
                res.redirect("/index?errorCode=1");
            }
        });
    } else if (action == "queryOne") {  //判断用户名是否重复
        req.models.user.find({loginId: ploginId}, function (err, user) {
            if (user.length == 1) {
                res.json("2");
            } else {
                res.json("-1");
            }
        });
    } else if (action == "insert") {  //添加一个新用户
        req.models.user.create({
            loginId: ploginId,
            password: ppassword
        }, function (err) {
            console.log(err);
            if (err) {
                res.json(2);
            } else {
                res.json(1);
            }
        });
    }

});

module.exports = router;
