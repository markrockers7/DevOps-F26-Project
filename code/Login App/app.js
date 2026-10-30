var checker = require('./account_verification');

//Setup sqlite to manage db connection/query
const sqlite3 = require('sqlite3').verbose();

const checkLoginQuery = 'SELECT * FROM ACCOUNT WHERE username = ? AND password = ?';
const createAccountQuery = 'INSERT INTO ACCOUNT (username, password) VALUES (?, ?)';

//Define function to connect to DB
const connectToDB = function() {
	return new sqlite3.Database('./login-store.db', sqlite3.OPEN_READWRITE, 
									(err) => {
									  if (err){
										  console.error('Connection error:', err.message);
										  process.exit(1)
									  }
									  console.log('Connected to the SQLite database.');
									}
								);
}

//Define function to close DB
const closeDB = function(db) {
	db.close((err) => {
	  if (err) {
		  return console.error('Closure error:', err.message);
	  }
	  console.log('Database connection closed.');
	});
}

//setup express to handle routing and rendering
var express = require('express');
var app = express();
app.set('view engine', 'ejs');

//setup bodyparser to extra data from form posts
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true })); 

//setup static public directory (to link images from view pages)
app.use(express.static('public'))

// route to login page
app.get('/', async function(req, res) {
	res.render('page/login', { error: '' });
});

// Checks if the username and password are valid
const checkLogin = async (db, sql, params) => {
	return new Promise((resolve, reject) => {
		db.get(sql, params, function(err, row) {
			if (err) {
				console.error('Error checking login:', err.message);
			  reject(err);
			}
			if (!row || row.length <= 0) {
				console.log('Login failed. Invalid username or password.');
				reject(new Error('Invalid username or password.'));
			} else {
				console.log('Login checked successfully.');
				console.log('username:', params[0]);
				console.log('password:', params[1]);
				console.log('---');
				resolve();
			}
		});
    });
};

// Attempts to create a new account with the provided username and password
const tryAccountCreation = async (db, sql, params) => {
	return new Promise((resolve, reject) => {
		db.run(sql, params, function(err) {
			if (err) {
				console.error('Error creating account:', err.message);
			  reject(err);
			}
			else {
				console.log('Account created successfully.');
				console.log('username:', params[0]);
				console.log('password:', params[1]);
				console.log('---');
				resolve();
			}
		});
    });
};

// route to api call for checking a username and password
app.post('/api/attempt_login', async function(req, res) {
	//setup params for query from form elements
	var usernameInput = req.body.username;
	var passwordInput = req.body.password;
	const params = [usernameInput, passwordInput]

	if (!checker(usernameInput, passwordInput)) {
		res.render('page/login', { error: 'Invalid username or password.' });
		return;
	}
	const db = new connectToDB();
		try {
			await checkLogin(db, checkLoginQuery, params);
			res.render('page/landingpage', {username: usernameInput});
		}
		catch (err) {
			console.log(err);
			res.render('page/login', { error: 'Invalid username or password.' });
		} finally {
			closeDB(db);
		}
});

// route to account creation page
app.get('/api/accountcreation', async function(req, res) {
	res.render('page/accountcreation', { error: '' });
});
// route to api call for attempting account creation
app.post('/api/attempt_account_creation', async function(req, res) {
	//setup params for query from form elements
	var usernameInput = req.body.username;
	var passwordInput = req.body.password;
	const params = [usernameInput, passwordInput]
	
	if (!checker(usernameInput, passwordInput)) {
		res.render('page/accountcreation', { error: 'Invalid username or password.' });
		return;
	}

	const db = new connectToDB();
	
	try {
		await tryAccountCreation(db, createAccountQuery, params);
		res.render('page/landingpage', {username: usernameInput});
	}
	catch (err) {
		console.log(err);
		res.render('page/accountcreation', { error: 'Account creation failed.' });
	} finally {
		closeDB(db);
	}
});

//start the web app
app.listen(8080, function () {
	console.log('App listening on port 8080!');
});
