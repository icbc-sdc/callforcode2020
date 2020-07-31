// const json = require('@/mock_data/data_simple.json');
const json = require('@/mock_data/data0729.json');
const timelineData = require('@/mock_data/timeline_data.json');
const weatherData = require('@/mock_data/weather.json');
const carData = require('@/mock_data/car.json');

export default {
  getCases() {
    return json;
  },
  getTimeline() {
    return timelineData;
  },
  getWeather() {
    return weatherData;
  },
  getCars() {
    return carData;
  }
};
