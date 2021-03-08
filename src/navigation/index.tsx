import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';

import {RootStackParamList} from '../types';
import MainTabNavigator from './MainTabNavigator';

export default () => (
  <NavigationContainer>
    <RootNavigator />
  </NavigationContainer>
);

const Stack = createStackNavigator<RootStackParamList>();

function RootNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        headerStyle: {
          backgroundColor: '#0c6157',
          shadowOpacity: 0,
        },
        headerTintColor: '#fff',
        headerTitleAlign: 'left',
        headerTitleStyle: {
          fontWeight: 'bold',
        },
      }}>
      <Stack.Screen name="Root" component={MainTabNavigator} />
    </Stack.Navigator>
  );
}
