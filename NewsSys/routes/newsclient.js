/**
 * Created by FiShelly on 2016/6/17.
 */
var express = require('express');
var router = express.Router();
/* 路由到客户端页面 */
router.get('/', function(req, res, next) {
    res.render('new_index.html',{csrf: req.csrfToken() });
});

module.exports = router;
