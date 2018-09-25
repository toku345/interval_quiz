const alexaTest = require("alexa-skill-test-framework");

alexaTest.initialize(
  require("../lambda/custom/index.js"),
  "amzn1.ask.skill.00000000-0000-0000-0000-000000000000",
  "amzn1.ask.account.VOID");

alexaTest.setLocale('ja-JP');

const intervals = ["M2", "M3", "P4", "P5", "M6", "M7"];

describe("音程クイズ Skill", function () {
  describe("LaunchRequest", function () {
    alexaTest.test([
      {
        request: alexaTest.getLaunchRequest(),
        saysLike: "音程クイズへようこそ。これから２つの音を再生するので、その音程を答えてください。問題。",
        repromptsLike: "音程を「メジャーセカンド」や「長３度」のように答えてください。",
        shouldEndSession: false,
        hasAttributes: {
          baseNote: "c",
          interval: interval => intervals.includes(interval)
        }
      }
    ]);
  });

  describe("AMAZON.YesIntent", function () {
    alexaTest.test([
      {
        request: alexaTest.getIntentRequest("AMAZON.YesIntent"),
        saysLike: "問題。",
        repromptsLike: "音程を「メジャーセカンド」や「長３度」のように答えてください。",
        shouldEndSession: false,
        hasAttributes: {
          baseNote: "c",
          interval: interval => intervals.includes(interval)
        }
      }
    ]);
  });

  describe("AMAZON.NoIntent", function () {
    alexaTest.test([
      {
        request: alexaTest.getIntentRequest("AMAZON.NoIntent"),
        saysLike: "また挑戦してくださいね！",
        shouldEndSession: true
      }
    ]);
  });

  describe("AMAZON.CancelIntent", function () {
    alexaTest.test([
      {
        request: alexaTest.getIntentRequest("AMAZON.CancelIntent"),
        saysLike: "また挑戦してくださいね！",
        shouldEndSession: true
      }
    ]);
  });

  describe("AMAZON.StopIntent", function () {
    alexaTest.test([
      {
        request: alexaTest.getIntentRequest("AMAZON.CancelIntent"),
        saysLike: "また挑戦してくださいね！",
        shouldEndSession: true
      }
    ]);
  });

  describe("CheckingAnswerIntent", function (){
    alexaTest.test([
      {
        request: alexaTest.getLaunchRequest(),
        saysLike: "音程クイズへようこそ。これから２つの音を再生するので、その音程を答えてください。問題。",
        repromptsLike: "音程を「メジャーセカンド」や「長３度」のように答えてください。",
        shouldEndSession: false,
        hasAttributes: {
          baseNote: "c",
          interval: interval => intervals.includes(interval)
        }
      },
      {
        request: alexaTest.getIntentRequest("CheckingAnswerIntent", { "Interval": "ちょうにど" }),
        saysLike: "正解",  // 「正解！ 」または「不正解」を含んでいること
        shouldEndSession: true
      }
    ]);
  });

  describe("AMAZON.HelpIntent", function () {
    const message =
          "音程クイズは音程トレーニング・スキルです。" +
          "これから２つの音を再生するので、その音程を答えてください。" +
          "出題してもよいでしょうか？";
    alexaTest.test([
      {
        request: alexaTest.getIntentRequest("AMAZON.HelpIntent"),
        saysLike: message,
        repromptsLike: message,
        shouldEndSession: false
      }
    ]);
  });
});
