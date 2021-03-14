import React from 'react';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';

import {MainTabParamList} from '../types';
import CameraScreen from '../screens/Camera';
import ChatsScreen from '../screens/Chats';
import StatusScreen from '../screens/Status';
import CallsScreen from '../screens/Calls';
import ExTopTabBar from '../components/ExTopTabBar';

const MainTab = createMaterialTopTabNavigator<MainTabParamList>();

const MainTabNavigator = () => {
  return (
    <MainTab.Navigator
      lazy={true}
      lazyPreloadDistance={1}
      initialRouteName="Chats"
      tabBarOptions={{
        activeTintColor: '#fff',
        style: {
          backgroundColor: '#0c6157',
        },
        indicatorStyle: {
          backgroundColor: '#fff',
          height: 3,
        },
        labelStyle: {
          fontWeight: 'bold',
        },
      }}
      tabBar={(props) => <ExTopTabBar {...props} />}>
      <MainTab.Screen name="Camera" component={CameraScreen} />
      <MainTab.Screen name="Chats" component={ChatsScreen} />
      <MainTab.Screen name="Status" component={StatusScreen} />
      <MainTab.Screen name="Calls" component={CallsScreen} />
    </MainTab.Navigator>
  );
};

export default MainTabNavigator;
