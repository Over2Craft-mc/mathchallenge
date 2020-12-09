# Math Challenge
 
 A minecraft plugin that ask each x seconds for players a mathematic addition : [5, 100] + [5,100]
 
 For example 
 
 `[Over2Math] How much is 20 + 20`
 
 `magrigry: 40`
 
 `[Over2Math] The answere was 40. Congratulation magrigry`

# Requirements 
* Spigot 1.16.4 (others version not tested)
* Vault
* Economy plugin (tested with CC3)

# Configuration 
```yaml
# Example -> [Over2Math] How much is 20 + 20
challenge_message: '§8[§6Over§b2§2Math§8] §cHow much is %s + %s ?'


# Example -> [Over2Math] The answere was 40. Congratulation playername
won_message: '§8[§6Over§b2§2Math§8]§c The answere was %s. Congratulation %s'


# Interval in seconds between each challenge
# 60 = 1 min ; 300 = 5 min
challenge_interval_in_seconds: 300

# How much money player win when he find the correct answere
challenge_money_reward: 100
```

