/**
 * Created by FiShelly on 2016/6/17.
 */
var express = require('express');
var router = express.Router();
/* 路由到后台管理首页 */
router.get('/', function(req, res, next) {
    //如果登录了则跳转到后台管理页
    //如果没登录则跳转到登录页面
    if (req.session.user) {
        res.render('nb_index.html',{loginId:req.session.user,csrf: req.csrfToken() });
    } else {
        res.redirect("/index?errorCode=2");
    }

});

module.exports = router;
