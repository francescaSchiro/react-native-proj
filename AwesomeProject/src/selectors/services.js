import { createSelector } from 'reselect';

export const selectGlobal = (state) => state;

export const selectBeer = createSelector(
  selectGlobal,
  (globalState) => {
    const { beers, selectedBeerId } = globalState.services;
    return beers && beers.find(item => item.id === selectedBeerId) || null;
  }
)

export const selectRandomBeer = createSelector(
  selectGlobal,
  (globalState) => {
    const { beers } = globalState.services;
    return beers ? beers[2] : null;
  }
)