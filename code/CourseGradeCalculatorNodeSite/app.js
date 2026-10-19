//setup express to handle routing and rendering
var express = require('express');
var app = express();
app.set('view engine', 'ejs');

//setup bodyparser to extra data from form posts
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true })); 

//setup static public directory (to link images from view pages)
app.use(express.static('public'))

// route to landing page
app.get('/', function(req, res) {
    res.render('page/landingpage');
});

// route to documentation page
app.get('/documentation', function(req, res) {
	res.render('page/documentation');
});

// route to quickstart page
app.get('/quickstart', function(req, res) {
	res.render('page/quickstart');
});

app.get('/createcourse', function(req, res) {
	res.render('page/features/createcourse');
});

app.get('/addgrade', function(req, res) {
	res.render('page/features/addgrade');
});

//start the web app
app.listen(8080, function () {
	console.log('App listening on port 8080!');
});

