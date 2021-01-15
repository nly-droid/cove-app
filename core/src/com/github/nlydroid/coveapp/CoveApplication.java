package com.github.nlydroid.coveapp;

import Screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class CoveApplication extends Game {
	public SpriteBatch batch;
	public static final int WORLD_WIDTH = 960;
  public static final int WORLD_HEIGHT = 640;
  public Array<String> questions;
  public Array<String> answers;
  public static final int numQuestion = 6;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
		questions = new Array<>(6);
		answers = new Array<>(6);
		questions.add("This internship is different from others. Explain what drew your interest and why it appeals to you?");
		questions.add("Talk a bit about the highlight from your educational experience so far." +
        " What has stood out about your program?");
    questions.add("What other activities are you involved outside of your studies?");
    questions.add("What skills/ or qualities do you contribute to a team? What role do you like to play in a team environment?");
    questions.add("What qualities or skills are you missing/ weak in that you would look for a teammate to support?");
    questions.add("Have you considered the ocean industry before? What has caught your attention this time?");

    answers.add("Workplace experience:\n\nProject-based work integrated learning model.\n" +
        "As the project could be completed within 7-8 weeks, I have the opportunity to finish what I start.\n");
    answers.add("Robotics Tournament:\n\n" +
        "I collaborated with two other teammates over several weeks to design the strategy, " +
        "develop the program in Aseba for a Thymio robot.\n\n" +
        "We also wrote a technical report, using Microsoft Word that includes a state transition diagram, " +
        "resulting in a weighted grade of 87, with high praise for the program description and analysis.");
    answers.add("Secretary of Dalhousie Art for Everyone Society. (2020/2021 academic year)\n\n" +
        "While most of the in-person activities are suspended in the fall semester, " +
        "I helped them get an official Dal domain to avoid spam emails and make online collaboration easier.");
    answers.add("Goal-oriented: \n\nI am focused and goal oriented. If I am passionate about a project, " +
        "I can contribute massive time and energy to reach the outcomes that I envision. " +
        "Nevertheless, I treasure efficiency, so I usually try to find better ways to do something or automate a task.");
    answers.add("Practical mindset:\n\nI love to suggest new ideas and way of doing things, but sometimes lack the resources and determination" +
        " to follow through with it. I would love to work with someone who are able " +
        "to transform discussions and ideas into practical activities.");
    answers.add("I have always had a fascination with the ocean. One reason I choose to study at Dalhousie " +
        "is probably because of Halifax is a lovely and lively coastal city. I love the ocean climate and its biodiversity.");

    answers.add("Teammates:\n\nWork with people from different backgrounds and perspectives.\n" +
        "As an international student, I love meeting people from a different culture and broadening my horizons.\n");
    answers.add("We achieved first-place in the tournament by " +
        "adopting an effective strategy, which involves robust mechanisms, error detection, " +
        "and recovery phases to adapt to different situations.\n\n" +
        "It was a project I spent a lot of time and effort in. Due to the competitiveness of the tournament, our team " +
        "set a goal to defeat as many other teams as possible. It was an overwhelming success and a dear memory of mine.");
    answers.add("Peer Mentor for the International Student Peer Mentorship Program (Dec 2020 - Now)\n\n" +
        "My job is to support first year and new to Dal international student.\n" +
        "I have not been an student employee for the International Center for a long time, but the staff there helped me a lot" +
        "when I just came to Canada, so now I would like to assist other international students as well.");
    answers.add("I do not have a specific role that I would like to play. About the leadership roles, " +
        "I think one can be both a follower and a leader. I would follow a good leader, and lead whenever the situations call for it. " +
        "Ultimately, what I follow is a vision. As long as we can reach it, I will assume the necessary role " +
        "if I have the competencies for it. My ideal team would be one with shared leadership," +
        " where everyone has a chance to develop their leadership skill and influence each other.");
    answers.add("Interpersonal skill:\n\nI am more of a task-oriented than a person-oriented teammate, " +
        "so I would like to learn how to communicate better " +
        "from outgoing and dynamic teammates with excellent interpersonal skills.");
    answers.add("I considered oceanography before, but I was not good at chemistry and physics. " +
        "In the end, I choose Computer Science as I prefer to develop efficient algorithms and " +
        "solutions to problems by programming but working in the ocean industry is still in the back of my mind. " +
        "One of my professors has told the class about his algorithm and machine learning projects at DeepSense, " +
        "so that is one of my inspiration as well.");
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}


}
