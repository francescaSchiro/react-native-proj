/*

ESLINT BASIC RULES
0: off
1: warn
2: error

*/
module.exports = {
  'env': {
    'node': true,
    'browser': true,
    'es6': true
  },
  'parser': 'babel-eslint',
  'extends': 'airbnb',
  'settings': {
    'import/resolver': {
      'babel-module': {
        'extensions': ['.js', '.jsx'],
        'paths': ['./src']
      }
    }
  },
  'rules': {
    'no-use-before-define': [0, { 'variables': false, 'functions': false, 'classes': false }],
    'no-shadow': 0,
    'global-require': 0,
    'comma-dangle': [1, {
      'arrays': 'always-multiline',
      'objects': 'always-multiline',
      'imports': 'always-multiline',
      'exports': 'always-multiline',
      'functions': 'always-multiline'
    }],
    'semi': [1, 'always', { "omitLastInOneLineBlock": true }],
    'spaced-comment': 0,
    'no-underscore-dangle': 0,
    'no-unused-expressions': 0,
    'arrow-parens': 0,
    'import/prefer-default-export': 0,
    'react/prop-types': 1,
    'eol-last': 1,
    'no-else-return': 1,
    'object-curly-newline': 0,
    'object-property-newline': 0,
    'implicit-arrow-linebreak': 0,
    'react/jsx-wrap-multilines': 0,
    'react/prefer-stateless-function': 0,
    'jsx-quotes': 0,
    'react/jsx-boolean-value': 0,
    'react/jsx-indent-props': 1,
    'react/jsx-first-prop-new-line': 0,
    'react/jsx-max-props-per-line': 0,
    'react/jsx-one-expression-per-line': 0,
    'react/jsx-closing-tag-location': 0,
    'react/jsx-closing-bracket-location': 0,
    'react/jsx-curly-brace-presence': 0,
    'react/destructuring-assignment': 1,
    'prefer-template': 1,
    'quotes': 1,
    'react/jsx-filename-extension': [1, { 'extensions': ['.js', '.jsx'] }],
  }
};