export const ACTION_TYPE = {
  GET_BEERS: 'GET_BEERS',
  RESULT_BEERS: 'RESULT_BEERS',
  SELECT_BEER: 'SELECT_BEER',
  RATE_BEER: 'RATE_BEER'
}

export const getBeers = () => ({
  type: ACTION_TYPE.GET_BEERS
})

export const resultBeers = data => ({
  type: ACTION_TYPE.RESULT_BEERS,
  data
})

export const selectBeer = data => ({
  type: ACTION_TYPE.SELECT_BEER,
  data
})

export const rateBeer = data => ({
  type: ACTION_TYPE.RATE_BEER,
  data
})

const initialState = {
  beers: null,
  selectedBeerId: null
}

const services = (state = initialState, action) => {
  switch (action.type) {
      case ACTION_TYPE.RESULT_BEERS: {
        const beers = state.beers ? state.beers : action.data;
        return {
          ...state,
          beers
        }
      }
      case ACTION_TYPE.SELECT_BEER:
        return {
          ...state,
          selectedBeerId: action.data.id
        }
      case ACTION_TYPE.RATE_BEER:
        const { item, rating } = action.data;
        const beers = state.beers.map((mappedBeer) => {
          if (mappedBeer.id === item.id) {
            return {
              ...mappedBeer,
              rating
            };
          } else {
            return mappedBeer;
          }          
        });
        return {
          ...state,
          beers
        }
    default:
      return state;
  }
}

export default services
