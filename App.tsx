/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import Navigation from './src/navigation';

import {StatusBar} from 'react-native';

import {SafeAreaProvider} from 'react-native-safe-area-context';

declare const global: {HermesInternal: null | {}};

const App = () => {
  return (
    <SafeAreaProvider>
      <Navigation />
      <StatusBar translucent backgroundColor="transparent" />
    </SafeAreaProvider>
  );
};

export default App;
