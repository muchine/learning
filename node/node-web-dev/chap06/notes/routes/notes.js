const util = require('util');
const express = require('express');
const router = express.Router();
const notes = require('../models/notes-memory');

// Add Note
router.get('/add', (req, res, next) => {
    res.render('notes/noteedit', {
        title: "Add a note",
        docreate: true,
        notekey: "",
        note: undefined
    });
});

// Edit note (update)
router.get('/edit', async (req, res, next) => {
    const note = await notes.read(req.query.key);
    res.render('notes/noteedit', {
        title: note ? ("Edit " + note.title) : "Add a note",
        docreate: false,
        notekey: req.query.key,
        note: note
    });
});

// Save Note (update)
router.post('/save', async (req, res, next) => {
    let note;
    if (req.body.docreate === "create") {
        note = await notes.create(req.body.notekey, req.body.title, req.body.body);
    } else {
        note = await notes.update(req.body.notekey, req.body.title, req.body.body);
    }

    res.redirect('/notes/view?key=' + req.body.notekey);
});

// Read Note (read)
router.get('/view', async (req, res, next) => {
    const note = await notes.read(req.query.key);
    res.render('notes/noteview', {
        title: note ? note.title : "",
        notekey: req.query.key,
        note: note
    });
});

// Ask to delete note (destroy)
router.get('/destroy', async (req, res, next) => {
    const note = await notes.read(req.query.key);
    res.render('notes/notedestroy', {
        title: note ? `Delete ${note.title}` : "",
        notekey: req.query.key,
        note: note
    });
});

// Really destroy note
router.post('/destroy/confirm', async (req, res, next) => {
    await notes.destroy(req.body.notekey);
    res.redirect('/');
});

module.exports = router;