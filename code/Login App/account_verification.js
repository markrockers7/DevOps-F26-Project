var account_verification = function(username, password) {
	if (username == null || username === '' || username === undefined) {
		return false;
	}
	if (password == null || password === '' || password === undefined) {
		return false;
	}
	return true;
};

module.exports = account_verification;