import React from 'react';
import { View, Text, ActivityIndicator } from 'react-native';
import { connect } from 'react-redux';
import Box from '../Components/Box';
import { getRandomBeer, getBeers, rateBeer } from '../reducers/services';
import { selectRandomBeer } from '../selectors/services';

class Home extends React.Component {

  _onFinishRating = (rating) => {
    this.props.rateBeer({item: this.props.beer, rating});
  }

  render() {
    if (!this.props.beer) {
      return (
        <View style={{backgroundColor: 'white', flex: 1,
          justifyContent: 'center',
          alignItems: 'center'}}>       
          <ActivityIndicator size='large' color='black' />   
        </View>
      )
    } else {
      return (
        <View style={{backgroundColor: 'white', flex: 1, justifyContent: 'center'}}>          
          <Box beer={this.props.beer} displayRating onFinishRating={ this._onFinishRating } />
        </View>
      )
    }
  }
}

function mapStateToProps(state) {
  return {
    beer: selectRandomBeer(state)
  }
}

function mapDispatchToProps(dispatch) {
  return {
    getBeers: () => dispatch(getBeers()),
    rateBeer: (item, rating) => dispatch(rateBeer(item, rating))
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home)