const express = require('express');
const router = express.Router();
const http = require('http');
const math = require('../domain/math');

router.get('/', function(req, res, next) {
    const fibonum = req.query.fibonum;
    if (fibonum) {
        renderResult(res, fibonum, math.fibonacci(fibonum), '/fibonacci')
    } else {
        renderEmpty(res, '/fibonacci')
    }
});

router.get('/async', function(req, res, next) {
    const fibonum = req.query.fibonum;
    if (fibonum) {
        // Calculate using async-aware function, in this server
        math.fibonacciAsync(fibonum, (err, fiboval) => {
           renderResult(res, fibonum, fiboval, '/fibonacci/async')
        });
    } else {
        renderEmpty(res, '/fibonacci/async');
    }
});

router.get('/rest', function(req, res, next) {
    const fibonum = req.query.fibonum;
    if (fibonum) {
        const httpreq = http.request({
            host: "localhost",
            port: process.env.SERVERPORT,
            path: "/fibonacci/" + Math.floor(req.query.fibonum),
            method: "GET"
        });

        httpreq.on('response', response => {
            response.on('data', chunk => {
                const data = JSON.parse(chunk);
                renderResult(res, fibonum, data.result, "/fibonacci/rest");
            });

            response.on('error', err => { next(err) });
        });

        httpreq.on('error', err => { next(err) });
        httpreq.end();
    } else {
        renderEmpty(res, "/fibonacci/rest")
    }
});

const renderResult = function(res, fibonum, fiboval, action) {
    // Calculate directly in this server
    res.render('fibonacci', {
        title: "Calculate Fibonacci numbers",
        fibonum: fibonum,
        fiboval: fiboval,
        action: action
    });
};

const renderEmpty = function(res, action) {
    res.render('fibonacci', {
        title: "Calculate Fibonacci numbers",
        fiboval: undefined,
        action: action
    });
};

module.exports = router;