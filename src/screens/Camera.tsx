import {NavigationProp} from '@react-navigation/core';
import React from 'react';
import {Platform, Text, View} from 'react-native';
import {
  requireNativeComponent,
  findNodeHandle,
  UIManager,
  PermissionsAndroid,
} from 'react-native';
import {PagerContext, PagerContextOptions} from '../components/ExPager';
import {RootStackParamList} from '../types';

interface Props {
  navigation: NavigationProp<RootStackParamList>;
  onPickFinished: Function | undefined;
  style?: object;
  children?: object;
}

const requestCameraPermission = async () => {
  if (Platform.OS === 'android') {
    try {
      const granted = await PermissionsAndroid.requestMultiple([
        PermissionsAndroid.PERMISSIONS.CAMERA,
        PermissionsAndroid.PERMISSIONS.RECORD_AUDIO,
        PermissionsAndroid.PERMISSIONS.READ_EXTERNAL_STORAGE,
      ]);
      if (
        granted[PermissionsAndroid.PERMISSIONS.CAMERA] ===
          PermissionsAndroid.RESULTS.GRANTED &&
        granted[PermissionsAndroid.PERMISSIONS.RECORD_AUDIO] ===
          PermissionsAndroid.RESULTS.GRANTED &&
        granted[PermissionsAndroid.PERMISSIONS.READ_EXTERNAL_STORAGE] ===
          PermissionsAndroid.RESULTS.GRANTED
      ) {
        console.log('camera is allowed');
      } else {
        console.log('camera is rejected');
      }
    } catch (err) {
      console.warn(err);
    }
  }
  return false;
};

class Camera extends React.Component<Props> {
  holder: any;

  private onPickFinished = (event: any) => {
    const data = event.nativeEvent;
    console.log('Received data', data);
    this.props.navigation.navigate('Contacts');
  };

  private create = () => {
    const containerId = findNodeHandle(this.holder);
    console.log('dispatch command with ' + containerId);
    UIManager.dispatchViewManagerCommand(
      containerId,
      UIManager.getViewManagerConfig('ImagePicker').Commands.create.toString(),
      [containerId],
    );
  };

  private openCamera = () => {
    const containerId = findNodeHandle(this.holder);
    UIManager.dispatchViewManagerCommand(
      containerId,
      UIManager.getViewManagerConfig('ImagePicker').Commands.open.toString(),
      [containerId],
    );
  };

  private closeCamera = () => {
    const containerId = findNodeHandle(this.holder);
    UIManager.dispatchViewManagerCommand(
      containerId,
      UIManager.getViewManagerConfig('ImagePicker').Commands.close.toString(),
      [containerId],
    );
  };
  componentDidMount() {
    setTimeout(() => {
      this.create();
    }, 1000);
  }

  render() {
    return requestCameraPermission() ? (
      <PagerContext.Consumer>
        {(context) => {
          console.log(context);
          const pContext = context as PagerContextOptions;
          pContext.addListener('enter', (value: number) => {
            if (value === 0) {
              this.openCamera();
            }
          });
          pContext.addListener('leave', (value: number) => {
            if (value === 0) {
              this.closeCamera();
            }
          });

          return (
            <Picker
              ref={(r) => (this.holder = r)}
              style={this.props.style}
              onPickFinished={this.onPickFinished}
              {...this.props}
            />
            // <View>
            //   <Text>Camera</Text>
            // </View>
          );
        }}
      </PagerContext.Consumer>
    ) : (
      <View>
        <Text>Permission failed</Text>
      </View>
    );
  }
}
const Picker = requireNativeComponent('ImagePicker');
export default Camera;
