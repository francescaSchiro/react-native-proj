import React from 'react';
import { View, FlatList, Text, ActivityIndicator } from 'react-native';
import { ListItem } from 'react-native-elements';
import { connect } from 'react-redux';
import { getBeers, selectBeer } from '../reducers/services';
import EventBridge from 'react-native-event-bridge';

class List extends React.Component {
  _onPress = (item) => {
    this.props.selectBeer(item);
    EventBridge.emitEvent(this, 'PresentDetail', { beerName: item.nameDisplay });
  }

  _renderSeparator = () => {
    return (
      <View
        style={{
          height: 1,
          backgroundColor: "#CED0CE"
        }}
      />
    );
  }
  
  componentDidMount() {
    
  }

  render() {
    if (!this.props.beers) {
      return (
        <View style={{backgroundColor: 'white', flex: 1,
          justifyContent: 'center',
          alignItems: 'center'}}>       
          <ActivityIndicator size='large' color='black' />   
        </View>
      )
    } else {
      return (      
        <FlatList
          keyExtractor={item => item.id}
          data={this.props.beers}
          ItemSeparatorComponent={this._renderSeparator}
          renderItem={({item}) => (
            <ListItem
              roundAvatar
              leftAvatar={{
                source: item.labels && item.labels.icon && { uri: item.labels && item.labels.icon },
              }}
              chevron={{color:'#CCC'}}
              title={item.name}
              onPress={ () => this._onPress(item)}
              subtitle={
                <View style={{backgroundColor: 'white', flex: 1, alignItems: 'flex-start'}}>
                  <Text>Scored {item.rating || 0}/5</Text>
                </View>
              }
            />
        )}/> 
      )
    }
  }
}

function mapStateToProps(state) {
  return {
    beers: state.services.beers
  }
}

function mapDispatchToProps(dispatch) {
  return {
    selectBeer: (item) => dispatch(selectBeer(item))
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(List)