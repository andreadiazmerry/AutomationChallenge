export const PASSWORD = 'secret_sauce';

export const validUsers = [
  'standard_user',
  'problem_user',
  'performance_glitch_user',
  'error_user',
  'visual_user',
];

export const invalidLogins = [
  { username: '', password: PASSWORD, message: 'Epic sadface: Username is required' },
  { username: 'locked_out_user', password: '', message: 'Epic sadface: Password is required' },
  { username: 'wrong_user', password: PASSWORD, message: 'Epic sadface: Username and password do not match any user in this service' },
  { username: 'performance_glitch_user', password: 'wrong_sauce', message: 'Epic sadface: Username and password do not match any user in this service' },
];

export const customer = { firstName: 'Andrea', lastName: 'Diaz', postalCode: '11001' };
