const alexaTest = require('alexa-skill-test-framework');

alexaTest.initialize(
  require('../lambda/custom/index.js'),
  "amzn1.ask.skill.00000000-0000-0000-0000-000000000000",
  "amzn1.ask.account.VOID");

describe("Hello World Skill", function () {
  describe("LaunchRequest", function () {
    alexaTest.test([
      {
        request: alexaTest.getLaunchRequest(),
        saysLike: '音程クイズへようこそ。サウンドを再生するので、その音程を答えてください！',
        repromptsLike: '音程クイズへようこそ。サウンドを再生するので、その音程を答えてください！',
        shouldEndSession: false
      }
    ]);
  });
});
