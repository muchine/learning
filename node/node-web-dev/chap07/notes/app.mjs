import fs from 'fs-extra';
import rfs from 'rotating-file-stream';
import createError from 'http-errors';
import express from'express';
import path from'path';
import cookieParser from 'cookie-parser';
import logger from'morgan';
import hbs from 'hbs';

import { router as indexRouter } from './routes/index';
// const usersRouter = require('./routes/users');
import { router as notesRouter } from './routes/notes';

// Workaround for loac of __dirname in ES6 modules
const __dirname = path.dirname(new URL(import.meta.url).pathname);

const app = express();

let logStream;

// Log to a file if requested
if (process.env.REQUEST_LOG_FILE) {
    (async () => {
        let logDirectory = path.dirname(process.env.REQUEST_LOG_FILE);
        await fs.ensureDir(logDirectory);
        logStream = rfs(process.env.REQUEST_LOG_FILE, {
          size: '10M',      // rotate every 10 MB written
          interval: '1d',   // rotate dialy
          compress: 'gzip'  // compress rotated files
        });
    })().catch(err => {
      console.error(err);
    });
}

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');
hbs.registerPartials(path.join(__dirname, 'views/partials'));

app.use(logger(process.env.REQUEST_LOG_FORMAT || 'dev', {
  stream: logStream ? logStream : process.stdout
}));

app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/assets/vendor/bootstrap', express.static(path.join(__dirname, 'node_modules', 'bootstrap', 'dist')));
app.use('/assets/vendor/jquery', express.static(path.join(__dirname, 'node_modules', 'jquery', 'dist')));
app.use('/assets/vendor/popper.js', express.static(path.join(__dirname, 'node_modules', 'popper.js', 'dist')));
app.use('/assets/vendor/feather-icons', express.static(path.join(__dirname, 'node_modules', 'feather-icons', 'dist')));

app.use('/', indexRouter);
// app.use('/users', usersRouter);
app.use('/notes', notesRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
    next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

export default app;
