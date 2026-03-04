-- FLAVOR 기준으로 FIRST_HALF에 ICECREAM_INFO를 left join 
select f.FLAVOR from FIRST_HALF f
join ICECREAM_INFO i on f.FLAVOR = i.FLAVOR
where f.TOTAL_ORDER > 3000 and i.INGREDIENT_TYPE = 'fruit_based' 


