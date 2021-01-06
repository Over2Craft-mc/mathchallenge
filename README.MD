# Math Challenge
 
 A minecraft plugin that ask each x seconds players to answere an addition : [5, 100] + [5,100]
 
 For example 
 
 `[Over2Math] How much is 20 + 20`
 
 `magrigry: 40`
 
 `[Over2Math] The answere was 40. Congratulation magrigry`

## Requirements 
* Spigot 1.16.4 (others version not tested)
* Vault
* Economy plugin (tested with CC3)

## Configuration 
```yaml
# challenge_message: '§8[§6Over§b2§2Math§8] §cHow much is %s + %s ?'
# Example -> [Over2Math] How much is 20 + 20
challenge_message: '§8[§6Over§b2§2Math§8] §cCombien font %s %s %s ?'

# won_message: '§8[§6Over§b2§2Math§8]§c The answere was %s. Congratulation %s you won 100 coins'
# Example -> [Over2Math] The answere was 40. Congratulation playername you won 100 coins
won_message: '§8[§6Over§b2§2Math§8]§c La réponse était %s. Bravo %s, tu gagnes %s Overs'

# Interval in seconds between each challenge
# 60 = 1 min ; 300 = 5 min
challenge_interval_in_seconds: 600

# How much money player win when he find the correct answere
challenge_money_reward: 
  multiplication: 300
  subtraction: 150
  addition: 100

# The reward will be challenge_money_reward + (challenge_money_reward * rewardMultiplicatorPerPlayer * playernumber)
rewardMultiplicatorPerPlayer: 0.05
```

## Download
https://over2craft-mc.github.io/mathchallenge/target/mathchallenge.jar
