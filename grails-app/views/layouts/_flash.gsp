<g:if test="${flash.success}">
    <div class="bg-success rounded"><p class="text-white">${flash.success}</p></div>
</g:if>
<g:if test="${flash.error}">
    <div class="bg-danger"><p class="text-white">${flash.error}</p></div>
</g:if>