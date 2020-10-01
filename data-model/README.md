# Modelo de datos

## Modelo Entidad Relación

![Modelo Entidad Relación](https://raw.githubusercontent.com/karianov/transport-management/master/data-model/erm-img.png)

## Script para la generación de la base de datos

En este [archivo SQL](https://github.com/karianov/transport-management/blob/master/data-model/erm-sql-script.sql) se encuentra el script que genera la base de datos de acuerdo al modelo expuesto anteriormente

## Solución de la consulta solicitada

```sql
SELECT 
    v.plate AS `Placa del vehículo`,
    it.name AS `Tipo de identificación de la empresa`,
    c.identification_number AS `Número de identificación de la empresa`,
    c.full_name AS `Nombre de la empresa`,
    (SELECT 
            COUNT(*)
        FROM
            transport_management_db.`vehicle-person` AS vp
        WHERE
            vp.fk_vehicle_id = v.vehicle_id) AS `Cantidad de conductores vinculados al vehículo`
FROM
    transport_management_db.vehicle AS v
        INNER JOIN
    transport_management_db.`company-vehicle` AS cp ON v.vehicle_id = cp.fk_vehicle_id
        INNER JOIN
    transport_management_db.company AS c ON cp.fk_company_id = c.company_id
        INNER JOIN
    transport_management_db.identification_type AS it ON c.fk_identification_type_id = it.identification_type_id
WHERE
    (SELECT 
            COUNT(*)
        FROM
            transport_management_db.`vehicle-person` AS vp
        WHERE
            vp.fk_vehicle_id = v.vehicle_id) > 2
ORDER BY v.plate ASC;
```