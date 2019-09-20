const FETCH_ERROR_TYPE = {
  UNAUTHORIZED: 'UNAUTHORIZED',
  INTERNAL_SERVER_ERROR: 'INTERNAL_SERVER_ERROR',
  NETWORK: 'NETWORK',
  TIMEOUT: 'TIMEOUT',
  GENERIC: 'GENERIC'
}

export function GETRequest(resource, params) {
  const queryStringParams = params ? params.reduce((acc, item) => {
    const key = Object.keys(item)
    return (`${acc}${acc === '' ? '?' : '&'}${key[0]}=${item[key[0]]}`)
  }, '') : '';
  return request(resource + queryStringParams, { method: 'GET' });
}

function request(resource, fetchOptions) {
  const fetchTimeout = 30000;

  return new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(errorWithFetchErrorType(FETCH_ERROR_TYPE.TIMEOUT));
    }, fetchTimeout);

    const fetchOptionsWithDefaults = {
      ...fetchOptions,
      headers: {
        ...fetchOptions.headers,
        'accept': 'application/json',
        'content-type': 'application/json'
      }
    };

    return fetch(resource, fetchOptionsWithDefaults).then(payload => {      
      if (payload.ok) {
        payload.json().then(response => {
          resolve({response});
        });
      } else {
        const fetchErrorType = fetchErrorTypeFromResponseStatus(payload.status);
        resolve(errorWithFetchErrorType(fetchErrorType));
      }
    }).catch((error) => {
      resolve(errorWithFetchErrorType(FETCH_ERROR_TYPE.NETWORK));
    })
  })
}

// Private methods

function errorWithFetchErrorType(fetchErrorType) {
  return {
    error: {
     fetchErrorType
    }
  };
}

function fetchErrorTypeFromResponseStatus(responseStatus) {
  if (responseStatus === 401) {
    return FETCH_ERROR_TYPE.UNAUTHORIZED;
  } else if (responseStatus === 500) {
    return FETCH_ERROR_TYPE.INTERNAL_SERVER_ERROR;
  } else {
    return FETCH_ERROR_TYPE.GENERIC;
  }
}