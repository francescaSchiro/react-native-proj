import React from 'react';
import { Text, View } from 'react-native';
import { Card, Rating } from 'react-native-elements';

class Box extends React.Component {

  _onFinishRating = (rating) => {
    this.props.onFinishRating(rating);
  }

  _imageUri = () => {
    if (this.props.beer.labels && this.props.beer.labels.medium) {
      return {uri: this.props.beer.labels.medium};
    }
    return require('../images/rectangle.png');
  }

  render() {
    return (      
      <View>
        <Card
          containerStyle={{borderWidth: 0}}
          title={this.props.beer.name}
          image={this._imageUri()}>
          <Text style={{marginBottom: 10}}>
            {this.props.beer.description || 'Description not available.'}
          </Text>
          {this.props.displayRating && 
            <Rating
              showRating
              onFinishRating={this._onFinishRating}
              imageSize={20}
              startingValue={this.props.beer.rating || 0} />
          }
        </Card>        
      </View>
    )
  }
}

export default Box;