package com.github.magikm18.reaction_tester;

import com.google.gson.GsonBuilder;

import java.io.Reader;

final class Config {
    RoundDefinition[] rounds;

    Config(Reader input) { rounds = new GsonBuilder().create().fromJson(input, RoundDefinition[].class);    }
}
