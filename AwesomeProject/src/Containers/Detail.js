import React from 'react';
import { View, Text } from 'react-native';
import Box from '../Components/Box';
import { connect } from 'react-redux';
import { selectBeer } from '../selectors/services';
import { rateBeer } from '../reducers/services';

class Detail extends React.Component {

  _onFinishRating = (item, rating) => {
    this.props.rateBeer({item, rating});
  }

  render() {
    if (!this.props.beer) {
      return (
        <View style={{backgroundColor: 'white', flex: 1, justifyContent: 'center', alignItems: 'center'}}>     
          <Text style={{fontSize: 34}}>No beer selected!</Text>     
        </View>
      )
    } else {
      return (
        <View style={{backgroundColor: 'white', flex: 1, justifyContent: 'center'}}>   
          <Box beer={this.props.beer} onFinishRating={(rating) => this._onFinishRating(this.props.beer, rating)}
            displayRating />
        </View>
      )
    }
  }
}

function mapStateToProps(state) {
  return {
    beer: selectBeer(state)
  }
}

function mapDispatchToProps(dispatch) {
  return {
    rateBeer: (item, rating) => dispatch(rateBeer(item, rating))
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Detail)