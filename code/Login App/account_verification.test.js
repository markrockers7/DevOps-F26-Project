var checker = require('./account_verification');

test('check null username valid password', () => {
    var username = null;
    var password = 'validpassword';
	expect(checker(username, password)).toBe(false);
});

test('check empty username valid password', () => {
    var username = '';
    var password = 'validpassword';
	expect(checker(username, password)).toBe(false);
});

test('check undefined username valid password', () => {
    var username = undefined;
    var password = 'validpassword';
	expect(checker(username, password)).toBe(false);
});

test('check valid username null password', () => {
    var username = 'validusername';
    var password = null;
	expect(checker(username, password)).toBe(false);
});

test('check valid username empty password', () => {
    var username = 'validusername';
    var password = '';
	expect(checker(username, password)).toBe(false);
});

test('check valid username undefined password', () => {
    var username = 'validusername';
    var password = undefined;
	expect(checker(username, password)).toBe(false);
});

test('check valid username valid password', () => {
    var username = 'validusername';
    var password = 'validpassword';
	expect(checker(username, password)).toBe(true);
});
