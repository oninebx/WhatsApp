import React from 'react';
import {PermissionStatus, Platform, Text, View} from 'react-native';
import {
  requireNativeComponent,
  findNodeHandle,
  UIManager,
  PermissionsAndroid,
} from 'react-native';

interface Props {
  style?: object;
  children?: object;
}

const requestCameraPermission = async () => {
  if (Platform.OS === 'android') {
    try {
      const granted = await PermissionsAndroid.requestMultiple([
        PermissionsAndroid.PERMISSIONS.CAMERA,
        PermissionsAndroid.PERMISSIONS.RECORD_AUDIO,
      ]);
      if (
        granted[PermissionsAndroid.PERMISSIONS.CAMERA] ===
          PermissionsAndroid.RESULTS.GRANTED &&
        granted[PermissionsAndroid.PERMISSIONS.RECORD_AUDIO] ===
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
  create = () => {
    const containerId = findNodeHandle(this.holder);
    console.log('dispatch command with ' + containerId);
    UIManager.dispatchViewManagerCommand(
      containerId,
      UIManager.getViewManagerConfig('ImagePicker').Commands.create.toString(),
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
      <Picker
        ref={(r) => (this.holder = r)}
        style={this.props.style}
        {...this.props}
      />
    ) : (
      <View>
        <Text>Permission failed</Text>
      </View>
    );
  }
}
const Picker = requireNativeComponent('ImagePicker');
export default Camera;
