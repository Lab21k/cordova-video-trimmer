var exec = require('cordova/exec');

var PLUGIN_NAME = 'VideoCrop';

// '/android_asset/www/audio/classic.mp3'

var VideoTrimmerPlugin = {
    trimVideo: function(srcPath, dstPath, startMs, endMs, cb) {
        exec(cb, null, PLUGIN_NAME, 'trimVideo', [srcPath, dstPath, startMs, endMs]);
    }
};

module.exports = VideoTrimmerPlugin;
