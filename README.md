# Stranger Lights

An Android application meant to send simple byte commands, with an optional array of light indices, to a connected Arduino device via bluetooth.

---

This app was designed to work specifically with an Arduino Uno device running [this sketch](https://gist.github.com/MCeley/1ec220f3dc6a109e11d6d911012ff6a8). The device has 26 WS2811 addressable LEDs connected to data pin 6 that are housed in an enclosure meant to mimic the string of Christmas lights in the first season of Stranger Things.

## Known Issues

 * Project doesn't support API 31+ bluetooth permissions.
 * Runtime permissions aren't implemented in the most user-friendly way.
 * Error handling is basically non-existent.